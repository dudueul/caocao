namespace Ngsm.SecuData.Internal
{
	public abstract class ISecuData
	{
		protected const long INVALID_SECU_KEY = 0L;

		protected long m_shandle;

		public abstract bool CreateSecuDataType(EUnityDataType eSecuDataType);

		public abstract void ReleaseSecuDataType();

		public abstract long GetSecuDataType();

		public abstract bool SetSecuDataType(long data);

		public abstract bool AddSecuDataType(long data);

		public abstract bool SubSecuDataType(long data);

		public abstract float GetSecuDataTypeFloat();

		public abstract bool SetSecuDataTypeFloat(float data);

		public abstract bool AddSecuDataTypeFloat(float data);

		public abstract bool SubSecuDataTypeFloat(float data);

		public abstract double GetSecuDataTypeDouble();

		public abstract bool SetSecuDataTypeDouble(double data);

		public abstract bool AddSecuDataTypeDouble(double data);

		public abstract bool SubSecuDataTypeDouble(double data);
	}
}
