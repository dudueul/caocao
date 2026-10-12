namespace Ngsm.SecuData
{
	public class NgsmDouble : NgsmSecuData<double>
	{
		public override double GetData()
		{
			return 0.0;
		}

		public override bool SetData(double data)
		{
			return false;
		}

		public override bool AddData(double data)
		{
			return false;
		}

		public override bool SubData(double data)
		{
			return false;
		}
	}
}
