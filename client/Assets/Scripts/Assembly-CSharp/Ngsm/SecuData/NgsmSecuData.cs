using Ngsm.SecuData.Internal;

namespace Ngsm.SecuData
{
	public abstract class NgsmSecuData<T>
	{
		protected const int INVALID_SECU_KEY = 0;

		protected int m_shandle;

		private static ISecuData secudata;

		protected static ISecuData SecuDataImpl
		{
			get
			{
				return null;
			}
		}

		protected NgsmSecuData()
		{
		}

		protected NgsmSecuData(EUnityDataType eUnityDataType)
		{
		}

		public abstract T GetData();

		public abstract bool SetData(T data);

		public abstract bool AddData(T data);

		public abstract bool SubData(T data);

		public void ReleaseData()
		{
		}

		protected void Dispose()
		{
		}
	}
}
