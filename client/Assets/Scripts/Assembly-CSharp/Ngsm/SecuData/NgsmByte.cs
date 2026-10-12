namespace Ngsm.SecuData
{
	public class NgsmByte : NgsmSecuData<byte>
	{
		public override byte GetData()
		{
			return 0;
		}

		public override bool SetData(byte data)
		{
			return false;
		}

		public override bool AddData(byte data)
		{
			return false;
		}

		public override bool SubData(byte data)
		{
			return false;
		}
	}
}
