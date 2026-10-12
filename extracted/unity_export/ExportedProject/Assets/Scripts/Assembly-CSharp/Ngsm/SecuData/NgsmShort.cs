namespace Ngsm.SecuData
{
	public class NgsmShort : NgsmSecuData<short>
	{
		public override short GetData()
		{
			return 0;
		}

		public override bool SetData(short data)
		{
			return false;
		}

		public override bool AddData(short data)
		{
			return false;
		}

		public override bool SubData(short data)
		{
			return false;
		}
	}
}
