#include <jni.h>

JNIEXPORT jint JNICALL Java_NativeSum_add(JNIEnv *env, jobject obj, jint a, jint b) {
    return a + b;
}