package com.example.android.miwok;

public class Word {
    //نعرف متغييريم جدد
    private String mMiowkTranslation;
    private String mEnglishTranslation;
    // قننشا متغير اسمه "لا يوجد صورة "قيمنه -1 نوعه ثابت مشترك
    private static final int NO_IMAG_PROVIDED = -1;
    //نضبط قية هذا النتغير على انه لا يوجد صورة فاخذ قيمة -1 اي تغير يطرا غالقيمة فبالتالي ان يوججد هناك صورة
    private int mImageResourceId = NO_IMAG_PROVIDED;
    private int maudioResource;


    //ننشأ كونستركتر
    public Word(String MiowkTranslation, String defultTransalation, int imageCode, int audio) {
        mMiowkTranslation = MiowkTranslation;
        mEnglishTranslation = defultTransalation;
        mImageResourceId = imageCode;
        maudioResource = audio;
    }

    public Word(String MiowkTranslation, String defultTransalation, int audio) {
        mMiowkTranslation = MiowkTranslation;
        mEnglishTranslation = defultTransalation;
        maudioResource = audio;

    }

    //نجلب قيمة متغير اللغة الغريبة
    public String getMiowkTranslation() {
        return mMiowkTranslation;
    }

    //نجلب قية اللغة الافتراضية
    public String getdefultTranslation() {
        return mEnglishTranslation;
    }

    public int getImageResourseId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAG_PROVIDED;
    }

    public int getaudio() {
        return maudioResource;
    }

}
// generate a toString methoud by ALT + insert
//يمكن لاستفادة منههها لطبع الاوبججكت هن
  /**@Override
  public String toString() {
    return "Word{" +
            "mMiowkTranslation='" + mMiowkTranslation + '\'' +
            ", mEnglishTranslation='" + mEnglishTranslation + '\'' +
            ", mImageResourceId=" + mImageResourceId +
            ", maudioResource=" + maudioResource +
            '}'; **/




