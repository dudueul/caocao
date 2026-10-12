// ObscuredTypes.cs — compat shim for CodeStage.AntiCheat.ObscuredTypes.
//
// [RE] Provenance: the original build serializes every balance field (Item
// assets: m_id, m_tier, m_price...) through CodeStage ObscuredInt/ObscuredString,
// whose *serialized* representation carries hiddenValue/currentCryptoKey/
// fakeValue/inited fields. This shim keeps the SAME serialized field names so
// existing .unity/.asset YAML loads unchanged, but decodes on access.
//
// Decode semantics (verified against decompiled CodeStage code in Phase 2;
// XOR scheme is the library's documented default for ObscuredInt):
//   plain = hiddenValue XOR currentCryptoKey   (cryptoKey 0 ⇒ plain == hiddenValue)
//
// See docs/lab/dsa/05-obfuscated-values.md for the full walkthrough.

using System;

namespace CodeStage.AntiCheat.ObscuredTypes
{
    [Serializable]
    public struct ObscuredInt
    {
        public int currentCryptoKey;   // serialized field, kept for YAML compat
        public int hiddenValue;        // serialized field
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
}

namespace CodeStage.AntiCheat.ObscuredTypes
{
    [Serializable]
    public struct ObscuredBool
    {
        public int currentCryptoKey;
        public int hiddenValue;    // bit-packed bool
        public bool inited;
        public bool fakeValue;

        public bool GetDecrypted() =>
            currentCryptoKey == 0 ? hiddenValue != 0 : (hiddenValue ^ currentCryptoKey) != 0;

        public static implicit operator bool(ObscuredBool o) => o.GetDecrypted();
        public static implicit operator ObscuredBool(bool v) => new ObscuredBool
        { hiddenValue = v ? 1 : 0, inited = true };
    }
}

namespace CodeStage.AntiCheat.ObscuredTypes
{
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
}
