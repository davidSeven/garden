package com.stream.garden.framework.register;

import com.stream.garden.framework.annotation.DynamicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author garden
 * @date 2020-06-08 9:12
 */
public class DynamicServiceRegister implements ImportBeanDefinitionRegistrar,
        ResourceLoaderAware, BeanClassLoaderAware, EnvironmentAware {
    private final Logger logger = LoggerFactory.getLogger(DynamicServiceRegister.class);

    private ResourceLoader resourceLoader;

    private ClassLoader classLoader;

    private Environment environment;

    public DynamicServiceRegister() {
        logger.debug("DynamicServiceRegister install");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        logger.debug("register begin");
        this.registerDefaultConfiguration(annotationMetadata, beanDefinitionRegistry);
        this.registerDynamicServices(annotationMetadata, beanDefinitionRegistry);
    }

    public void registerDefaultConfiguration(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        Map<String, Object> attributes = metadata.getAnnotationAttributes(EnableDynamicService.class.getName(), true);
        if (null != attributes && attributes.containsKey("defaultConfiguration")) {
            String name;
            if (metadata.hasEnclosingClass()) {
                name = "default." + metadata.getEnclosingClassName();
            } else {
                name = "default." + metadata.getClassName();
            }
            this.registerDynamicConfiguration(registry, name, attributes.get("defaultConfiguration"));
        }
    }

    public void registerDynamicServices(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        ClassPathScanningCandidateComponentProvider scanner = getScanner();
        scanner.setResourceLoader(this.resourceLoader);

        Set<String> basePackages;

        AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(DynamicService.class);

        scanner.addIncludeFilter(annotationTypeFilter);
        basePackages = getBasePackages(metadata);

        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidateComponents = scanner
                    .findCandidateComponents(basePackage);
            for (BeanDefinition candidateComponent : candidateComponents) {
                if (candidateComponent instanceof AnnotatedBeanDefinition) {
                    // verify annotated class is an interface
                    AnnotatedBeanDefinition beanDefinition = (AnnotatedBeanDefinition) candidateComponent;
                    AnnotationMetadata annotationMetadata = beanDefinition.getMetadata();
                    Assert.isTrue(annotationMetadata.isInterface(), "@DynamicService can only be specified on an interface");

                    Map<String, Object> attributes = annotationMetadata.getAnnotationAttributes(DynamicService.class.getCanonicalName());
                    String name = getName(attributes);
                    Object configuration = null;
                    if (null != attributes) {
                        configuration = attributes.get("configuration");
                    }
                    this.registerDynamicConfiguration(registry, name, configuration);
                    this.registerDynamicConfiguration(registry, annotationMetadata, attributes);
                }
            }
        }
    }

    private String getName(Map<String, Object> attributes) {
        if (null == attributes) {
            return null;
        }
        String name = (String) attributes.get("value");
        if (!StringUtils.hasText(name)) {
            name = (String) attributes.get("name");
        }
        return name;
    }

    private void registerDynamicConfiguration(BeanDefinitionRegistry registry, Object name, Object configuration) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(DynamicServiceSpecification.class);
        builder.addConstructorArgValue(name);
        builder.addConstructorArgValue(configuration);
        registry.registerBeanDefinition(name + "." + DynamicServiceSpecification.class.getSimpleName(), builder.getBeanDefinition());
    }

    private void registerDynamicConfiguration(BeanDefinitionRegistry registry, AnnotationMetadata annotationMetadata, Map<String, Object> attributes) {
        String className = annotationMetadata.getClassName();
        BeanDefinitionBuilder definition = BeanDefinitionBuilder.genericBeanDefinition(DynamicServiceFactoryBean.class);

        String name = getName(attributes);
        definition.addPropertyValue("name", name);
        definition.addPropertyValue("type", className);
        definition.addPropertyValue("fallback", attributes.get("fallback"));
        definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);

        String alias = name + "DynamicService";
        AbstractBeanDefinition beanDefinition = definition.getBeanDefinition();

        beanDefinition.setPrimary(true);

        BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, className, new String[]{alias});
        BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
    }

    protected ClassPathScanningCandidateComponentProvider getScanner() {
        return new ClassPathScanningCandidateComponentProvider(false, this.environment) {

            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                if (beanDefinition.getMetadata().isIndependent()) {
                    if (beanDefinition.getMetadata().isInterface()
                            && beanDefinition.getMetadata().getInterfaceNames().length == 1
                            && Annotation.class.getName().equals(beanDefinition.getMetadata().getInterfaceNames()[0])) {
                        try {
                            Class<?> target = ClassUtils.forName(beanDefinition.getMetadata().getClassName(), DynamicServiceRegister.this.classLoader);
                            return !target.isAnnotation();
                        } catch (Exception ex) {
                            this.logger.error("Could not load target class: " + beanDefinition.getMetadata().getClassName(), ex);
                        }
                    }
                    return true;
                }
                return false;
            }
        };
    }

    protected Set<String> getBasePackages(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> attributes = importingClassMetadata.getAnnotationAttributes(EnableDynamicService.class.getCanonicalName());
        if (null == attributes) {
            return Collections.emptySet();
        }

        Set<String> basePackages = new HashSet<>();
        for (String pkg : (String[]) attributes.get("value")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }

        if (basePackages.isEmpty()) {
            basePackages.add(ClassUtils.getPackageName(importingClassMetadata.getClassName()));
        }
        return basePackages;
    }
}
