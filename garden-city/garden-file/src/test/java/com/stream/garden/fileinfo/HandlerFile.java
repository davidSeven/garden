package com.stream.garden.fileinfo;

public interface HandlerFile {

    /**
     * 写入文件
     */
    void write();

    /**
     * 获取文件大小
     *
     * @return 文件大小
     */
    long getSize();

    /**
     * 清理
     */
    void clear();
}
