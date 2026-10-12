namespace Ngsm.SecuData
{
	public class NgsmInt64 : NgsmSecuData<long>
	{
		public override long GetData()
		{
			return 0L;
		}

		public override bool SetData(long data)
		{
			return false;
		}

		public override bool AddData(long data)
		{
			return false;
		}

		public override bool SubData(long data)
		{
			return false;
		}
	}
}
