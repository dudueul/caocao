namespace Ngsm.SecuData
{
	public class NgsmFloat : NgsmSecuData<float>
	{
		public override float GetData()
		{
			return 0f;
		}

		public override bool SetData(float data)
		{
			return false;
		}

		public override bool AddData(float data)
		{
			return false;
		}

		public override bool SubData(float data)
		{
			return false;
		}
	}
}
