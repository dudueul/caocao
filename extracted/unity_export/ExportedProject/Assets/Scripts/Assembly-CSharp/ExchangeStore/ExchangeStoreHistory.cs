using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

namespace ExchangeStore
{
	public class ExchangeStoreHistory : ITransmittable
	{
		private ObscuredULong m_srl;

		private ObscuredULong m_userId;

		private ObscuredInt m_shopId;

		private ObscuredInt m_shopSlot;

		private ObscuredInt m_purchaseCount;

		private DateTime m_updDate;

		private DateTime m_regDate;

		public ulong srl
		{
			get
			{
				return 0uL;
			}
		}

		public ulong userId
		{
			get
			{
				return 0uL;
			}
		}

		public int shopId
		{
			get
			{
				return 0;
			}
		}

		public int shopSlot
		{
			get
			{
				return 0;
			}
		}

		public int purchaseCount
		{
			get
			{
				return 0;
			}
		}

		public DateTime updDate
		{
			get
			{
				return default(DateTime);
			}
		}

		public DateTime regDate
		{
			get
			{
				return default(DateTime);
			}
		}

		protected override void DeserializeImpl(JsonData jsonData)
		{
		}
	}
}
