package com.sky.framework.dao.aspect;

import com.sky.framework.api.context.RequestContext;
import com.sky.framework.api.context.SqlContext;
import com.sky.framework.dao.annotation.DataScope;
import com.sky.framework.utils.AnnotationUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class DataScopeAspect {

    /*
     * @Aspect          作用是把当前类标识为一个切面供容器读取
     *
     * @Pointcut        切入点
     * @Before          标识一个前置增强方法，相当于BeforeAdvice的功能，相似功能的还有
     * @AfterReturning  后置增强，相当于AfterReturningAdvice，方法正常退出时执行
     * @AfterThrowing   异常抛出增强，相当于ThrowsAdvice
     * @After           final增强，不管是抛出异常或者正常退出都会执行
     * @Around          环绕增强，相当于MethodInterceptor
     * @DeclareParents  引介增强，相当于IntroductionInterceptor
     */
    public DataScopeAspect() {
    }

    @Pointcut("@annotation(com.sky.framework.dao.annotation.DataScope)")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        Annotation annotation = getAnnotation(joinPoint, DataScope.class);
        DataScope dataScope = (DataScope) annotation;
        if (null != annotation) {
            RequestContext requestContext = RequestContext.getCurrentContext();
            SqlContext sqlContext = new SqlContext();
            requestContext.setSqlContext(sqlContext);
        }
    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        RequestContext.getCurrentContext().setSqlContext(null);
    }

    /**
     * <table border="0">
     *   <tbody>
     *     <tr>
     *       <td><span style="color: rgba(51, 153, 102, 1)">all</span></td>
     *       <td>to suppress all warnings</td>
     *     </tr>
     *     <tr>
     *       <td><span style="color: rgba(51, 153, 102, 1)">boxing&nbsp;</span></td>
     *       <td>to suppress warnings relative to boxing/unboxing operations</td>
     *     </tr>
     *     <tr>
     *       <td><span style="color: rgba(51, 153, 102, 1)">cast</span></td>
     *       <td>to suppress warnings relative to cast operations</td>
     *     </tr>
     *     <tr>
     *       <td><span style="color: rgba(51, 153, 102, 1)">dep-ann</span></td>
     *       <td>to suppress warnings relative to deprecated annotation</td>
     *     </tr>
     *     <tr>
     *       <td><span style="color: rgba(51, 153, 102, 1)">deprecation</span></td>
     *       <td>to suppress warnings relative to deprecation</td>
     *     </tr>
     *     <tr>
     *       <td><span style="color: rgba(51, 153, 102, 1)">fallthrough</span></td>
     *       <td>to suppress warnings relative to missing breaks in switch statements</td>
     *     </tr>
     *     <tr>
     *       <td><span style="color: rgba(51, 153, 102, 1)">finally&nbsp;</span></td>
     *       <td>to suppress warnings relative to finally block that don’t return</td>
     *     </tr>
     *     <tr>
     *       <td><span style="color: rgba(51, 153, 102, 1)">hiding</span></td>
     *       <td>to suppress warnings relative to locals that hide variable</td>
     *     </tr>
     *     <tr>
     *       <td><span style="color: rgba(51, 153, 102, 1)">incomplete-switch</span></td>
     *       <td>to suppress warnings relative to missing entries in a switch statement (enum case)</td>
     *     </tr>
     *     <tr>
     *       <td><span style="color: rgba(51, 153, 102, 1)">nls</span></td>
     *       <td>to suppress warnings relative to non-nls string literals</td>
     *     </tr>
     *     <tr>
     *       <td><span style="color: rgba(51, 153, 102, 1)">null</span></td>
     *       <td>to suppress warnings relative to null analysis</td>
     *     </tr>
     *     <tr>
     *       <td><span style="color: rgba(51, 153, 102, 1)">rawtypes</span></td>
     *       <td>to suppress warnings relative to un-specific types when using generics on class params</td>
     *     </tr>
     *     <tr>
     *       <td><span style="color: rgba(51, 153, 102, 1)">restriction</span></td>
     *       <td>to suppress warnings relative to usage of discouraged or forbidden references</td>
     *     </tr>
     *     <tr>
     *       <td><span style="color: rgba(51, 153, 102, 1)">serial</span></td>
     *       <td>to suppress warnings relative to missing serialVersionUID field for a serializable class</td>
     *     </tr>
     *     <tr>
     *       <td><span style="color: rgba(51, 153, 102, 1)">static-access</span></td>
     *       <td>o suppress warnings relative to incorrect static access</td>
     *     </tr>
     *     <tr>
     *       <td><span style="color: rgba(51, 153, 102, 1)">synthetic-access&nbsp;</span></td>
     *       <td>to suppress warnings relative to unoptimized access from inner classes</td>
     *     </tr>
     *     <tr>
     *       <td><span style="color: rgba(51, 153, 102, 1)">unchecked</span></td>
     *       <td>to suppress warnings relative to unchecked operations</td>
     *     </tr>
     *     <tr>
     *       <td><span style="color: rgba(51, 153, 102, 1)">unqualified-field-access</span></td>
     *       <td>to suppress warnings relative to field access unqualified</td>
     *     </tr>
     *     <tr>
     *       <td><span style="color: rgba(51, 153, 102, 1)">unused</span></td>
     *       <td>to suppress warnings relative to unused code</td>
     *     </tr>
     *   </tbody>
     * </table>
     *
     * @param joinPoint       joinPoint
     * @param annotationClass annotationClass
     * @return Annotation
     */
    @SuppressWarnings({"all"})
    public Annotation getAnnotation(JoinPoint joinPoint, Class<? extends Annotation> annotationClass) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        return AnnotationUtil.getAnnotation(method, annotationClass);
    }
}
