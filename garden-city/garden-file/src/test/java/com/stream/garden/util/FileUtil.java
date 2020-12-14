package com.stream.garden.util;

import com.stream.garden.config.FileConfig;
import com.stream.garden.dto.BasFileInfo;
import com.stream.garden.dto.BasFileInfoUploadItemDto;
import com.stream.garden.dto.FileParameterDto;
import com.stream.garden.fileinfo.HandlerFile;
import com.stream.garden.fileinfo.HandlerFileFactory;
import com.stream.garden.fileinfo.HandlerImageConfig;
import com.stream.garden.framework.api.exception.CommonException;
import net.coobird.thumbnailator.util.ThumbnailatorUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public final class FileUtil {
    /**
     * 文件路径
     */
    public static final String SEPARATOR = "/";
    public static final String REVERSE_SEPARATOR = "\\";
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
    /**
     * 文件名与后缀名的分隔符
     */
    private static final String SPLIT_SYMBOL = ".";
    private static final SimpleDateFormat SDF_YYYYMMDD = new SimpleDateFormat("yyyyMMdd");

    /**
     * 获取文件扩展名，不包含"."点
     *
     * @param fileName 文件名
     * @return 文件扩展名
     */
    public static String getFileExtName(String fileName) {
        if (fileName.lastIndexOf(SPLIT_SYMBOL) != -1) {
            return fileName.substring(fileName.lastIndexOf(SPLIT_SYMBOL) + 1);
        }
        return "";
    }

    /**
     * 获取文件名，不包含后缀
     *
     * @param fileName 文件名
     * @return 文件名
     */
    public static String getFileName(String fileName) {
        if (fileName.lastIndexOf(SPLIT_SYMBOL) != -1) {
            return fileName.substring(0, fileName.lastIndexOf(SPLIT_SYMBOL));
        }
        return fileName;
    }

    /**
     * 检查不可上传文件
     *
     * @param excludes                    检查类型
     * @param multipartHttpServletRequest 文件
     * @return boolean
     */
    public static boolean hasExcludes(Set<String> excludes, MultipartHttpServletRequest multipartHttpServletRequest) {
        MultiValueMap<String, MultipartFile> multiFileMap = multipartHttpServletRequest.getMultiFileMap();
        if (multiFileMap.isEmpty() || CollectionUtils.isEmpty(multiFileMap.values())) {
            return false;
        }
        boolean has = false;
        Collection<MultipartFile> values = multiFileMap.values().iterator().next();
        if (CollectionUtils.isNotEmpty(values)) {
            for (MultipartFile multipartFile : values) {
                if (null != multipartFile && StringUtils.isNotEmpty(multipartFile.getOriginalFilename())) {
                    String fileExtName = FileUtil.getFileExtName(multipartFile.getOriginalFilename());
                    if (excludes.contains(fileExtName)) {
                        has = true;
                        break;
                    }
                }
            }
        }
        return has;
    }

    /**
     * 转换参数
     *
     * @param multipartHttpServletRequest 文件请求
     * @return 文件参数
     */
    public static FileParameterDto convertFileParameter(MultipartHttpServletRequest multipartHttpServletRequest, List<BasFileInfoUploadItemDto> itemList) {
        FileParameterDto fileParameter = new FileParameterDto();
        fileParameter.setFiles(convert(multipartHttpServletRequest, itemList));
        if (CollectionUtils.isNotEmpty(fileParameter.getFiles())) {
            fileParameter.setNum(fileParameter.getFiles().size());
        }
        return fileParameter;
    }

    private static List<BasFileInfo> convert(MultipartHttpServletRequest multipartHttpServletRequest, List<BasFileInfoUploadItemDto> itemList) {
        List<BasFileInfo> files = new ArrayList<>();
        MultiValueMap<String, MultipartFile> multiFileMap = multipartHttpServletRequest.getMultiFileMap();
        if (multiFileMap.isEmpty() || CollectionUtils.isEmpty(multiFileMap.values())) {
            return null;
        }
        List<MultipartFile> multipartFileList = multiFileMap.values().iterator().next();
        if (CollectionUtils.isEmpty(multipartFileList)) {
            return null;
        }
        // 处理附加的文件信息
        Map<Integer, BasFileInfoUploadItemDto> itemMap = new HashMap<>(16);
        if (CollectionUtils.isNotEmpty(itemList)) {
            for (int i = 0; i < itemList.size(); i++) {
                itemMap.put(i, itemList.get(i));
            }
        }
        int index = 0;
        for (MultipartFile multipartFile : multipartFileList) {
            BasFileInfo file = new BasFileInfo();
            if (multipartFile.isEmpty()) {
                continue;
            }
            // 上传的文件名
            file.setOriginalName(multipartFile.getOriginalFilename());
            file.setContentType(multipartFile.getContentType());
            file.setSize(multipartFile.getSize());
            try {
                file.setBytes(multipartFile.getBytes());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            // 文件扩展名
            if (StringUtils.isNotEmpty(file.getOriginalName())) {
                file.setExtendName(getFileExtName(file.getOriginalName()));
                if (StringUtils.isEmpty(file.getName())) {
                    file.setName(getFileName(file.getOriginalName()));
                }
            }
            // 处理文件附加信息
            BasFileInfoUploadItemDto item = itemMap.get(index);
            if (null != item) {
                file.setRemark(item.getRemark());
            }
            files.add(file);
            index++;
        }
        return files;
    }

    /**
     * 文件上传，本地处理
     *
     * @param fileConfig         文件配置
     * @param handlerImageConfig 图片相关配置
     * @param fileParameter      文件参数
     * @return MsdResponse
     */
    public static List<BasFileInfo> uploadLocal(FileConfig fileConfig, HandlerImageConfig handlerImageConfig, FileParameterDto fileParameter) {
        List<BasFileInfo> files = fileParameter.getFiles();
        if (CollectionUtils.isNotEmpty(files)) {
            String savePath = getNowDateDirs();
            // 创建保存文件目录
            String attachImagesFilePath = fileConfig.getAttachImagesFilePath();
            String dirPath;
            if (attachImagesFilePath.endsWith("/")) {
                dirPath = attachImagesFilePath + savePath;
            } else {
                dirPath = attachImagesFilePath + SEPARATOR + savePath;
            }
            mkdirsDir(dirPath);
            // 保存文件
            for (BasFileInfo file : files) {
                if (null == file || null == file.getBytes()) {
                    continue;
                }
                file.setBizId(fileParameter.getBizId());
                file.setBizCode(fileParameter.getBizCode());
                try {
                    // 文件名称
                    String fileName = getRandomFileName() + SPLIT_SYMBOL + file.getExtendName();
                    // 文件存储路径
                    String filePath = dirPath + SEPARATOR + fileName;
                    /*------------------------------文件处理---------------------------------*/
                    HandlerFile handlerFile = new HandlerFileFactory(filePath, file.getBytes(), file.getExtendName(), handlerImageConfig).builder();
                    // 写入文件
                    handlerFile.write();
                    // 文件大小
                    file.setSize(handlerFile.getSize());
                    // 清理文件缓存
                    handlerFile.clear();
                    /*------------------------------文件处理---------------------------------*/
                    // 文件访问地址
                    file.setVisitPath(savePath + SEPARATOR + fileName);
                    // 文件存储地址
                    file.setPhysicalPath(filePath);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    throw new CommonException("999", "上传文件失败");
                }
            }
            clean(files);
        }
        return files;
    }

    private static void clean(List<BasFileInfo> files) {
        if (CollectionUtils.isNotEmpty(files)) {
            for (BasFileInfo file : files) {
                file.setBytes(null);
            }
        }
    }

    /**
     * 创建一个随机的文件夹名
     *
     * @return 文件名（不包含后缀）
     */
    public static String getRandomFileName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 创建文件夹
     *
     * @param dirPath 文件夹路径
     */
    private static void mkdirsDir(String dirPath) {
        dirPath = FilenameUtils.normalize(dirPath);
        java.io.File dir = new java.io.File(dirPath);
        if (!dir.exists()) {
            boolean mkdirs = dir.mkdirs();
            if (!mkdirs) {
                throw new CommonException("999", "创建文件夹失败");
            }
        }
    }

    /**
     * 日期文件夹名/小时文件夹名
     *
     * @return 日期文件夹名/小时文件夹名
     */
    public static String getNowDateDirs() {
        synchronized (SDF_YYYYMMDD) {
            Date date = new Date();
            return SDF_YYYYMMDD.format(date) + "/" + getHours(date);
        }
    }

    private static int getHours(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 是否为图片
     *
     * @param extendName 后缀
     * @return boolean
     */
    public static boolean isImage(String extendName) {
        return ThumbnailatorUtils.isSupportedOutputFormat(extendName);
    }

    public static void close(OutputStream os) {
        try {
            if (os != null) {
                os.close();
            }
        } catch (IOException ioe) {
            logger.error(ioe.getMessage(), ioe);
        }
    }

    public static void close(InputStream is) {
        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException ioe) {
            logger.error(ioe.getMessage(), ioe);
        }
    }

    /**
     * 读取文件
     *
     * @param files 文件信息
     * @return 文件
     */
    public static List<BasFileInfo> getFileList(List<BasFileInfo> files) {
        if (CollectionUtils.isEmpty(files)) {
            return files;
        }
        for (BasFileInfo file : files) {
            writeBytes(file);
        }
        return files;
    }

    public static BasFileInfo getFileByte(BasFileInfo fileInfo) {
        if (null == fileInfo) {
            return null;
        }
        writeBytes(fileInfo);
        return fileInfo;
    }

    private static void writeBytes(BasFileInfo fileInfo) {
        // 文件路径
        String filePath = fileInfo.getPhysicalPath();
        java.io.File downFile = new java.io.File(filePath);
        if (downFile.exists()) {
            try {
                byte[] downFileData = FileUtils.readFileToByteArray(downFile);
                if (downFileData != null && downFileData.length > 2) {
                    fileInfo.setSize((long) downFileData.length);
                    fileInfo.setBytes(downFileData);
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}
