using System;
using System.Runtime.InteropServices;
using UnityEngine;

namespace CodeStage.AntiCheat.ObscuredTypes
{
	[Serializable]
	[StructLayout((LayoutKind)0, Size = 24)]
	public struct ObscuredQuaternion
	{
		[Serializable]
		[StructLayout((LayoutKind)0, Size = 16)]
		public struct RawEncryptedQuaternion
		{
			public int x;

			public int y;

			public int z;

			public int w;
		}

		private static int cryptoKey;

		private static readonly Quaternion initialFakeValue;

		[SerializeField]
		private int currentCryptoKey;

		[SerializeField]
		private RawEncryptedQuaternion hiddenValue;

		[SerializeField]
		private bool inited;

		private ObscuredQuaternion(RawEncryptedQuaternion value)
		{
			currentCryptoKey = 0;
			hiddenValue = default(RawEncryptedQuaternion);
			inited = false;
		}

		public static void SetNewCryptoKey(int newKey)
		{
		}

		public static RawEncryptedQuaternion Encrypt(Quaternion value)
		{
			return default(RawEncryptedQuaternion);
		}

		public static RawEncryptedQuaternion Encrypt(Quaternion value, int key)
		{
			return default(RawEncryptedQuaternion);
		}

		public static Quaternion Decrypt(RawEncryptedQuaternion value)
		{
			return default(Quaternion);
		}

		public static Quaternion Decrypt(RawEncryptedQuaternion value, int key)
		{
			return default(Quaternion);
		}

		public void ApplyNewCryptoKey()
		{
		}

		public void RandomizeCryptoKey()
		{
		}

		public RawEncryptedQuaternion GetEncrypted()
		{
			return default(RawEncryptedQuaternion);
		}

		public void SetEncrypted(RawEncryptedQuaternion encrypted)
		{
		}

		private Quaternion InternalDecrypt()
		{
			return default(Quaternion);
		}

		private bool CompareQuaternionsWithTolerance(Quaternion q1, Quaternion q2)
		{
			return false;
		}

		public static implicit operator ObscuredQuaternion(Quaternion value)
		{
			return default(ObscuredQuaternion);
		}

		public static implicit operator Quaternion(ObscuredQuaternion value)
		{
			return default(Quaternion);
		}

		public override int GetHashCode()
		{
			return 0;
		}

		public override string ToString()
		{
			return null;
		}

		public string ToString(string format)
		{
			return null;
		}
	}
}
