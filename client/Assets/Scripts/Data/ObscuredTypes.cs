// [RE] CodeStage.AntiCheat.ObscuredTypes — rebuild shim.
// The original serialized every balance field through these wrappers
// (Item.m_id, m_price, ... all ObscuredInt; see dump.cs TypeDefIndex 11545).
// Serialized field names (currentCryptoKey/hiddenValue/inited/fakeValue) are
// kept byte-compatible with the original YAML so existing .unity/.asset load.
//
// Decode semantics (CodeStage default): plain = hiddenValue XOR cryptoKey;
// cryptoKey == 0 means stored plain. Full walkthrough: docs/lab/dsa/05.
using System;
using UnityEngine;

namespace CodeStage.AntiCheat.ObscuredTypes
{
    public interface IObscuredType
    {
    }

    [Serializable]
    public struct ObscuredInt
    {
        public int currentCryptoKey;
        public int hiddenValue;
        public bool inited;
        public int fakeValue;
        public int prevValue;

        public int GetDecrypted() =>
            currentCryptoKey == 0 ? hiddenValue : hiddenValue ^ currentCryptoKey;

        public static implicit operator int(ObscuredInt o) => o.GetDecrypted();
        public static implicit operator ObscuredInt(int v) => new ObscuredInt
        { hiddenValue = v, currentCryptoKey = 0, inited = true };

        public override string ToString() => GetDecrypted().ToString();
    }

    [Serializable]
    public struct ObscuredUInt
    {
        public uint currentCryptoKey;
        public uint hiddenValue;
        public bool inited;
        public uint fakeValue;

        public uint GetDecrypted() =>
            currentCryptoKey == 0 ? hiddenValue : hiddenValue ^ currentCryptoKey;

        public static implicit operator uint(ObscuredUInt o) => o.GetDecrypted();
        public static implicit operator ObscuredUInt(uint v) => new ObscuredUInt
        { hiddenValue = v, inited = true };
    }

    [Serializable]
    public struct ObscuredLong
    {
        public long currentCryptoKey;
        public long hiddenValue;
        public bool inited;
        public long fakeValue;

        public long GetDecrypted() =>
            currentCryptoKey == 0 ? hiddenValue : hiddenValue ^ currentCryptoKey;

        public static implicit operator long(ObscuredLong o) => o.GetDecrypted();
        public static implicit operator ObscuredLong(long v) => new ObscuredLong
        { hiddenValue = v, currentCryptoKey = 0, inited = true };
    }

    [Serializable]
    public struct ObscuredULong
    {
        public ulong currentCryptoKey;
        public ulong hiddenValue;
        public bool inited;
        public ulong fakeValue;

        public ulong GetDecrypted() =>
            currentCryptoKey == 0 ? hiddenValue : hiddenValue ^ currentCryptoKey;

        public static implicit operator ulong(ObscuredULong o) => o.GetDecrypted();
        public static implicit operator ObscuredULong(ulong v) => new ObscuredULong
        { hiddenValue = v, inited = true };
    }

    [Serializable]
    public struct ObscuredShort
    {
        public short currentCryptoKey;
        public short hiddenValue;
        public bool inited;
        public short fakeValue;

        public short GetDecrypted() =>
            currentCryptoKey == 0 ? hiddenValue : (short)(hiddenValue ^ currentCryptoKey);

        public static implicit operator short(ObscuredShort o) => o.GetDecrypted();
        public static implicit operator ObscuredShort(short v) => new ObscuredShort
        { hiddenValue = v, inited = true };
    }

    [Serializable]
    public struct ObscuredUShort
    {
        public ushort currentCryptoKey;
        public ushort hiddenValue;
        public bool inited;
        public ushort fakeValue;

        public ushort GetDecrypted() =>
            currentCryptoKey == 0 ? hiddenValue : (ushort)(hiddenValue ^ currentCryptoKey);

        public static implicit operator ushort(ObscuredUShort o) => o.GetDecrypted();
        public static implicit operator ObscuredUShort(ushort v) => new ObscuredUShort
        { hiddenValue = v, inited = true };
    }

    [Serializable]
    public struct ObscuredSByte
    {
        public sbyte currentCryptoKey;
        public sbyte hiddenValue;
        public bool inited;
        public sbyte fakeValue;

        public sbyte GetDecrypted() =>
            currentCryptoKey == 0 ? hiddenValue : (sbyte)(hiddenValue ^ currentCryptoKey);

        public static implicit operator sbyte(ObscuredSByte o) => o.GetDecrypted();
        public static implicit operator ObscuredSByte(sbyte v) => new ObscuredSByte
        { hiddenValue = v, inited = true };
    }

    [Serializable]
    public struct ObscuredByte
    {
        public byte currentCryptoKey;
        public byte hiddenValue;
        public bool inited;
        public byte fakeValue;

        public byte GetDecrypted() =>
            currentCryptoKey == 0 ? hiddenValue : (byte)(hiddenValue ^ currentCryptoKey);

        public static implicit operator byte(ObscuredByte o) => o.GetDecrypted();
        public static implicit operator ObscuredByte(byte v) => new ObscuredByte
        { hiddenValue = v, inited = true };
    }

    [Serializable]
    public struct ObscuredChar
    {
        public char currentCryptoKey;
        public char hiddenValue;
        public bool inited;
        public char fakeValue;

        public char GetDecrypted() =>
            currentCryptoKey == default(char) ? hiddenValue : (char)(hiddenValue ^ currentCryptoKey);

        public static implicit operator char(ObscuredChar o) => o.GetDecrypted();
        public static implicit operator ObscuredChar(char v) => new ObscuredChar
        { hiddenValue = v, inited = true };
    }

    [Serializable]
    public struct ObscuredBool
    {
        public int currentCryptoKey;
        public int hiddenValue;
        public bool inited;
        public bool fakeValue;

        public bool GetDecrypted() =>
            currentCryptoKey == 0 ? hiddenValue != 0 : (hiddenValue ^ currentCryptoKey) != 0;

        public static implicit operator bool(ObscuredBool o) => o.GetDecrypted();
        public static implicit operator ObscuredBool(bool v) => new ObscuredBool
        { hiddenValue = v ? 1 : 0, inited = true };
    }

    [Serializable]
    public struct ObscuredFloat
    {
        public int currentCryptoKey;
        public int hiddenValue;    // bit-packed float
        public byte extraValue;
        public bool inited;
        public float fakeValue;

        public float GetDecrypted()
        {
            int bits = currentCryptoKey == 0 ? hiddenValue : hiddenValue ^ currentCryptoKey;
            return BitConverter.Int32BitsToSingle(bits);
        }

        public static implicit operator float(ObscuredFloat o) => o.GetDecrypted();
        public static implicit operator ObscuredFloat(float v) => new ObscuredFloat
        { hiddenValue = BitConverter.SingleToInt32Bits(v), inited = true };
    }

    [Serializable]
    public struct ObscuredDouble
    {
        public long currentCryptoKey;
        public long hiddenValue;   // bit-packed double
        public bool inited;
        public double fakeValue;

        public double GetDecrypted()
        {
            long bits = currentCryptoKey == 0 ? hiddenValue : hiddenValue ^ currentCryptoKey;
            return BitConverter.Int64BitsToDouble(bits);
        }

        public static implicit operator double(ObscuredDouble o) => o.GetDecrypted();
        public static implicit operator ObscuredDouble(double v) => new ObscuredDouble
        { hiddenValue = BitConverter.DoubleToInt64Bits(v), inited = true };
    }

    [Serializable]
    public struct ObscuredDecimal
    {
        public long currentCryptoKey;
        public long hiddenValueA;
        public long hiddenValueB;
        public bool inited;
        public decimal fakeValue;

        public decimal GetDecrypted()
        {
            // [RE] CodeStage packs decimal into two longs; rebuild stores the
            // decimal bits directly when key==0 and degrades gracefully.
            if (currentCryptoKey == 0 && hiddenValueA == 0 && hiddenValueB == 0)
                return fakeValue;
            return fakeValue;   // full decimal decode lands with Phase 2 crypto notes
        }

        public static implicit operator decimal(ObscuredDecimal o) => o.GetDecrypted();
        public static implicit operator ObscuredDecimal(decimal v) => new ObscuredDecimal
        { fakeValue = v, inited = true };
    }

    [Serializable]
    public struct ObscuredString
    {
        public string currentCryptoKey;
        public string hiddenValue;
        public bool inited;
        public string fakeValue;

        public string GetDecrypted()
        {
            if (string.IsNullOrEmpty(hiddenValue)) return hiddenValue;
            if (string.IsNullOrEmpty(currentCryptoKey)) return hiddenValue;
            // [RE] XOR with repeating key bytes — confirm in Phase 2 decompile
            var key = currentCryptoKey;
            var chars = hiddenValue.ToCharArray();
            for (int i = 0; i < chars.Length; i++)
                chars[i] = (char)(chars[i] ^ key[i % key.Length]);
            return new string(chars);
        }

        public static implicit operator string(ObscuredString o) => o.GetDecrypted();
        public static implicit operator ObscuredString(string v) => new ObscuredString
        { hiddenValue = v, inited = true };

        public override string ToString() => GetDecrypted();
    }

    [Serializable]
    public struct ObscuredQuaternion
    {
        public int currentCryptoKey;
        public float x, y, z, w;   // [RE] stub keeps components; crypto per-axis
        public bool inited;
        public Quaternion fakeValue;

        public Quaternion GetDecrypted() => new Quaternion(x, y, z, w);

        public static implicit operator Quaternion(ObscuredQuaternion o) => o.GetDecrypted();
        public static implicit operator ObscuredQuaternion(Quaternion v) => new ObscuredQuaternion
        { x = v.x, y = v.y, z = v.z, w = v.w, inited = true };
    }

    [Serializable]
    public struct ObscuredVector3
    {
        public int currentCryptoKey;
        public float x, y, z;
        public bool inited;
        public Vector3 fakeValue;

        public Vector3 GetDecrypted() => new Vector3(x, y, z);

        public static implicit operator Vector3(ObscuredVector3 o) => o.GetDecrypted();
        public static implicit operator ObscuredVector3(Vector3 v) => new ObscuredVector3
        { x = v.x, y = v.y, z = v.z, inited = true };
    }

    [Serializable]
    public struct ObscuredVector2
    {
        public int currentCryptoKey;
        public float x, y;
        public bool inited;
        public Vector2 fakeValue;

        public Vector2 GetDecrypted() => new Vector2(x, y);

        public static implicit operator Vector2(ObscuredVector2 o) => o.GetDecrypted();
        public static implicit operator ObscuredVector2(Vector2 v) => new ObscuredVector2
        { x = v.x, y = v.y, inited = true };
    }

    [Serializable]
    public struct ObscuredEnum
    {
        public int currentCryptoKey;
        public int hiddenValue;
        public bool inited;

        public int GetDecrypted() =>
            currentCryptoKey == 0 ? hiddenValue : hiddenValue ^ currentCryptoKey;

        public static implicit operator int(ObscuredEnum o) => o.GetDecrypted();
        public static implicit operator ObscuredEnum(int v) => new ObscuredEnum
        { hiddenValue = v, inited = true };
    }

    // [RE] PlayerPrefs wrapper — the original stored saves through it
    // (crypto per key). Rebuild: transparent passthrough to PlayerPrefs.
    public static class ObscuredPrefs
    {
        public static void SetInt(string key, int value) => PlayerPrefs.SetInt(key, value);
        public static int GetInt(string key, int defaultValue = 0) => PlayerPrefs.GetInt(key, defaultValue);
        public static void SetString(string key, string value) => PlayerPrefs.SetString(key, value);
        public static string GetString(string key, string defaultValue = "") => PlayerPrefs.GetString(key, defaultValue);
        public static void SetFloat(string key, float value) => PlayerPrefs.SetFloat(key, value);
        public static float GetFloat(string key, float defaultValue = 0f) => PlayerPrefs.GetFloat(key, defaultValue);
        public static bool HasKey(string key) => PlayerPrefs.HasKey(key);
        public static void DeleteKey(string key) => PlayerPrefs.DeleteKey(key);
        public static void Save() => PlayerPrefs.Save();
    }

}
