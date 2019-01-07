package com.example.jumping_i.sudoku_original.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SharedPrefManager {
    private static final String TAG = SharedPrefManager.class.getSimpleName();
    private static final String PREF_KEY = "PREF_KEY";

    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private static SharedPrefManager mInstance;
    private SharedPreferences mPreferences;
    private Context mContext;


    public enum PREF_KEYS {
        REG_ID
        /** SharedPreference 테스트 키 */
        , TEST_SHARED_KEY_STRING
        , TEST_SHARED_KEY_INT
        , TEST_SHARED_KEY_BOOLEAN
        /** 현재 게임 상태 데이터를 저장 한다.(게임중 / 정지) */
        , SUDOKU_GAME_STATE_DATA

    }

    /*******************************************************************************
     * init.
     *******************************************************************************/
    private SharedPrefManager() {

    }

    private SharedPrefManager(Context context) {
        this.mContext = context;
        mPreferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
    }

    public static SharedPrefManager getInstance(Context ctx) {
        if (mInstance == null) {
            return new SharedPrefManager(ctx);
        }
        return mInstance;
    }

    /*******************************************************************************
     * Public Method.
     *******************************************************************************/
    /**
     * SharedPreference의 모든값을 삭제한다.
     *
     * @return true is success
     */
    public boolean clearSharedValue() {
        if (mPreferences == null) {
            return false;
        }
        try {
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.clear();
            editor.apply();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 전달받은 Key값의 Data를 반환한다. (Boolean)
     *
     * @param eKey
     *            : Key값
     * @param bDefault
     *            : 실패시 전달받은 default값
     * @return boolean value
     */
    public boolean getSharedValueByBoolean(PREF_KEYS eKey, boolean bDefault) {
        String strShareKey = eKey.toString();
        if (!checkParam(strShareKey)) {
            return bDefault;
        }
        try {
            return mPreferences.getBoolean(strShareKey, bDefault);
        } catch (Exception e) {
            return bDefault;
        }
    }

    /**
     * 전달받은 Key값의 Data를 반환한다. (Float)
     *
     * @param eKey
     *            : Key값
     * @param fDefault
     *            : 실패시 전달받은 default값
     * @return float value
     */
    public float getSharedValueByFloat(PREF_KEYS eKey, float fDefault) {
        String strShareKey = eKey.toString();
        if (!checkParam(strShareKey)) {
            return fDefault;
        }
        try {
            return mPreferences.getFloat(strShareKey, fDefault);
        } catch (Exception e) {
            return fDefault;
        }
    }

    /**
     * 전달받은 Key값의 Data를 반환한다. (Integer)
     *
     * @param eKey
     *            : Key값
     * @param nDefault
     *            : 실패시 전달받은 default값
     * @return int value
     */
    public int getSharedValueByInt(PREF_KEYS eKey, int nDefault) {
        String strShareKey = eKey.toString();
        if (!checkParam(strShareKey)) {
            return nDefault;
        }
        try {
            return mPreferences.getInt(strShareKey, nDefault);
        } catch (Exception e) {
            return nDefault;
        }
    }

    /**
     * 전달받은 Key값의 Data를 반환한다. (Long)
     *
     * @param eKey
     *            : Key값
     * @param lDefault
     *            : 실패시 전달받은 default값
     * @return long value
     */
    public long getSharedValueByLong(PREF_KEYS eKey, long lDefault) {
        String strShareKey = eKey.toString();
        if (!checkParam(strShareKey)) {
            return lDefault;
        }
        try {
            return mPreferences.getLong(strShareKey, lDefault);
        } catch (Exception e) {
            return lDefault;
        }
    }

    /**
     * 전달받은 Key값의 Data를 반환한다. (String)
     *
     * @param eKey
     *            : Key값
     * @param strDefault
     *            : 실패시 전달받은 default값
     * @return String value
     */
    public String getSharedValueByString(PREF_KEYS eKey, String strDefault) {
        String strShareKey = eKey.toString();
        if (!checkParam(strShareKey)) {
            return strDefault;
        }
        try {
            return mPreferences.getString(strShareKey, strDefault);
        } catch (Exception e) {
            return strDefault;
        }
    }

    /**
     * 전달받은 Key값의 Data를 반환한다. (Set<String>)
     *
     * @param eKey
     *            : Key값
     * @param strDefault
     *            : 실패시 전달받은 default값
     * @return Set<String> value
     */
    public Set<String> getSharedValueByStringSet(PREF_KEYS eKey, Set<String> strDefault) {
        String strShareKey = eKey.toString();
        if (!checkParam(strShareKey)) {
            return strDefault;
        }
        try {
            return mPreferences.getStringSet(strShareKey, strDefault);
        } catch (Exception e) {
            return strDefault;
        }
    }

    /**
     * 전달받은 Key값의 Data를 List 형식으로 반환한다. (List<E>)
     * @param eKey
     * @param defaultValue
     * @param <E>
     * @return List<E>
     */
    public <E> List<E> getJsonArrayList(PREF_KEYS eKey, List<E> defaultValue) {
        String strShareKey = eKey.toString();
        if (!checkParam(strShareKey)) {
            return defaultValue;
        }
        String json = mPreferences.getString(strShareKey, null);
        List<E> valueList = new ArrayList<>();

        if (json != null) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    valueList.add( (E) jsonArray.opt(i));
                }
            } catch (JSONException e) {
                Log.e(TAG, e.toString());
            }
        }
        return valueList;
    }

    /**
     * 전달받은 Key값으로 Data를 저장한다. (Boolean)
     *
     * @param eKey
     *            : Key값
     * @param bValue
     *            : 저장할 Data
     * @return true is success
     */
    public boolean setSharedValueByBoolean(PREF_KEYS eKey, boolean bValue) {
        String strShareKey = eKey.toString();
        if (!checkParam(strShareKey)) {
            return false;
        }
        try {
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putBoolean(strShareKey, bValue);
            editor.apply();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 전달받은 Key값으로 Data를 저장한다. (Float)
     *
     * @param eKey
     *            : Key값
     * @param fValue
     *            : 저장할 Data
     * @return true is success
     */
    public boolean setSharedValueByFloat(PREF_KEYS eKey, float fValue) {
        String strShareKey = eKey.toString();
        if (!checkParam(strShareKey)) {
            return false;
        }
        try {
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putFloat(strShareKey, fValue);
            editor.apply();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 전달받은 Key값으로 Data를 저장한다. (Integer)
     *
     * @param eKey
     *            : Key값
     * @param nValue
     *            : 저장할 Data
     * @return true is success
     */
    public boolean setSharedValueByInt(PREF_KEYS eKey, int nValue) {
        String strShareKey = eKey.toString();
        if (!checkParam(strShareKey)) {
            return false;
        }
        try {
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putInt(strShareKey, nValue);
            editor.apply();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 전달받은 Key값으로 Data를 저장한다. (Long)
     *
     * @param eKey
     *            : Key값
     * @param lValue
     *            : 저장할 Data
     * @return true is success
     */
    public boolean setSharedValueByLong(PREF_KEYS eKey, long lValue) {
        String strShareKey = eKey.toString();
        if (!checkParam(strShareKey)) {
            return false;
        }
        try {
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putLong(strShareKey, lValue);
            editor.apply();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 전달받은 Key값으로 Data를 저장한다. (String)
     *
     * @param eKey
     *            : Key값
     * @param strValue
     *            : 저장할 Data
     * @return true is success
     */
    public boolean setSharedValueByString(PREF_KEYS eKey, String strValue) {
        String strShareKey = eKey.toString();
        if (!checkParam(strShareKey)) {
            return false;
        }
        try {
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putString(strShareKey, strValue);
            editor.apply();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 전달받은 Key값으로 Data를 저장한다. (Set<String>)
     *
     * @param eKey
     *            : Key값
     * @param strValue
     *            : 저장할 Data
     * @return true is success.
     */
    public boolean setSharedValueByStringSet(PREF_KEYS eKey, Set<String> strValue) {
        String strShareKey = eKey.toString();
        if (!checkParam(strShareKey)) {
            return false;
        }
        try {
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putStringSet(strShareKey, strValue);
            editor.apply();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 전달받은 Key값으로 Data를 List 형식으로 저장한다. (List<E>)
     * @param eKey
     * @param valueList
     * @param <E>
     * @return true is success
     */
    public <E> boolean setJsonArrayList(PREF_KEYS eKey, List<E> valueList) {
        String strShareKey = eKey.toString();
        if (!checkParam(strShareKey)) {
            return false;
        }

        try {
            JSONArray jsonArray = new JSONArray();
            for (E value : valueList) {
                jsonArray.put(value);
            }

            if (!valueList.isEmpty()) {
                mPreferences.edit().putString(strShareKey, jsonArray.toString()).apply();
            } else {
                mPreferences.edit().putString(strShareKey, null).apply();
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /*******************************************************************************
     * Private Method.
     *******************************************************************************/
    /**
     * Key값 및 mPreferences 객체 판단.
     *
     * @param strShareKey
     *            : Key값
     * @return true is ok.
     */
    private boolean checkParam(String strShareKey) {
        if (strShareKey == null || strShareKey.trim().length() < 1) {
            return false;
        }
        if (mPreferences == null) {
            return false;
        }

        return true;
    }
}
