using System.Runtime.InteropServices;

namespace Ngsm.SecuData.Internal
{
	public class SecuDataNative : ISecuData
	{
		[PreserveSig]
		public static extern long CreateSecuData(int cESecuDataTye);

		[PreserveSig]
		public static extern void ReleaseSecuData(long sHandle);

		[PreserveSig]
		public static extern void ReleaseSecuDataAll();

		[PreserveSig]
		public static extern bool SetSecuData(long sHandle, long cSecuValue);

		[PreserveSig]
		public static extern long GetSecuData(long sHandle);

		[PreserveSig]
		public static extern bool AddSecuData(long sHandle, long cSecuValue);

		[PreserveSig]
		public static extern bool SubSecuData(long sHandle, long cSecuValue);

		[PreserveSig]
		public static extern bool SetSecuDataFloat(long sHandle, float cSecuValue);

		[PreserveSig]
		public static extern float GetSecuDataFloat(long sHandle);

		[PreserveSig]
		public static extern bool AddSecuDataFloat(long sHandle, float cSecuValue);

		[PreserveSig]
		public static extern bool SubSecuDataFloat(long sHandle, float cSecuValue);

		[PreserveSig]
		public static extern bool SetSecuDataDouble(long sHandle, double cSecuValue);

		[PreserveSig]
		public static extern double GetSecuDataDouble(long sHandle);

		[PreserveSig]
		public static extern bool AddSecuDataDouble(long sHandle, double cSecuValue);

		[PreserveSig]
		public static extern bool SubSecuDataDouble(long sHandle, double cSecuValue);

		public override bool CreateSecuDataType(EUnityDataType eSecuDataType)
		{
			return false;
		}

		public override void ReleaseSecuDataType()
		{
		}

		public override long GetSecuDataType()
		{
			return 0L;
		}

		public override bool SetSecuDataType(long data)
		{
			return false;
		}

		public override bool AddSecuDataType(long data)
		{
			return false;
		}

		public override bool SubSecuDataType(long data)
		{
			return false;
		}

		public override float GetSecuDataTypeFloat()
		{
			return 0f;
		}

		public override bool SetSecuDataTypeFloat(float data)
		{
			return false;
		}

		public override bool AddSecuDataTypeFloat(float data)
		{
			return false;
		}

		public override bool SubSecuDataTypeFloat(float data)
		{
			return false;
		}

		public override double GetSecuDataTypeDouble()
		{
			return 0.0;
		}

		public override bool SetSecuDataTypeDouble(double data)
		{
			return false;
		}

		public override bool AddSecuDataTypeDouble(double data)
		{
			return false;
		}

		public override bool SubSecuDataTypeDouble(double data)
		{
			return false;
		}
	}
}
