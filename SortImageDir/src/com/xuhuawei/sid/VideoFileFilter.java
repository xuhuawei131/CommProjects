package com.xuhuawei.sid;

import java.io.File;
import java.io.FilenameFilter;

public class VideoFileFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        if (name.startsWith(".")){
            return false;
        }else{
            return  name.endsWith(".mp4");
        }
    }
}
