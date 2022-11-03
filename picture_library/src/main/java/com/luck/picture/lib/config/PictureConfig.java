package com.luck.picture.lib.config;

/**
 * @author：luck
 * @data：2017/5/24 下午1:00
 * @描述: 常量类
 */
public final class PictureConfig {
    public static final int APPLY_STORAGE_PERMISSIONS_CODE = 1;
    public static final int APPLY_CAMERA_PERMISSIONS_CODE = 2;
    public static final int APPLY_AUDIO_PERMISSIONS_CODE = 3;

    public final static String EXTRA_SELECT_IMAGES_KEY = "selectImagesKey";
    public final static String EXTRA_MEDIA_KEY = "mediaKey";
    public final static String EXTRA_AUDIO_PATH = "audioPath";
    public final static String EXTRA_VIDEO_PATH = "videoPath";
    public final static String EXTRA_PREVIEW_VIDEO = "isExternalPreviewVideo";
    public final static String EXTRA_PREVIEW_DELETE_POSITION = "position";
    public final static String EXTRA_FC_TAG = "picture";
    public final static String EXTRA_RESULT_SELECTION = "extra_result_media";
    public final static String EXTRA_PREVIEW_SELECT_LIST = "previewSelectList";
    public final static String EXTRA_SELECT_LIST = "selectList";
    public final static String EXTRA_POSITION = "position";
    public final static String EXTRA_DIRECTORY_PATH = "directory_path";
    public final static String EXTRA_BUNDLE_CAMERA_PATH = "CameraPath";
    public final static String EXTRA_BUNDLE_ORIGINAL_PATH = "OriginalPath";
    public final static String EXTRA_BOTTOM_PREVIEW = "bottom_preview";
    public final static String EXTRA_CONFIG = "PictureSelectorConfig";

    public final static String CAMERA_FACING = "android.intent.extras.CAMERA_FACING";

    public final static int CAMERA_BEFORE = 1;
    public final static int CAMERA_AFTER = 2;

    public final static int TYPE_ALL = 0;
    public final static int TYPE_IMAGE = 1;
    public final static int TYPE_VIDEO = 2;
    public final static int TYPE_AUDIO = 3;

    public static final int MAX_COMPRESS_SIZE = 100;

    public static final int TYPE_CAMERA = 1;
    public static final int TYPE_PICTURE = 2;

    public final static int SINGLE = 1;
    public final static int MULTIPLE = 2;

    public final static int CHOOSE_REQUEST_IMAGE = 181;
    public final static int CHOOSE_REQUEST_VIDEO = 182;
    public final static int CHOOSE_REQUEST_AUDIO = 183;
    public final static int REQUEST_CAMERA = 909;
}
