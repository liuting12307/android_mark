LOCAL_PATH :=$(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE :=jni-test
LOCAL_SRC_FILES :=JniTest.c utils.c
LOCAL_LDLIBS :=-llog

include $(BUILD_SHARED_LIBRARY)