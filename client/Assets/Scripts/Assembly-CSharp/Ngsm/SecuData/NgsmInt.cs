namespace Ngsm.SecuData
{
	public class NgsmInt : NgsmSecuData<int>
	{
		public override int GetData()
		{
			return 0;
		}

		public override bool SetData(int data)
		{
			return false;
		}

		public override bool AddData(int data)
		{
			return false;
		}

		public override bool SubData(int data)
		{
			return false;
		}
	}
}
