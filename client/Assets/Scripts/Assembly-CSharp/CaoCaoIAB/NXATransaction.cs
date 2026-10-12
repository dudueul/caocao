using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

namespace CaoCaoIAB
{
	public class NXATransaction : ITransmittable
	{
		private ObscuredInt m_amount;

		private ObscuredString m_authorizeUrl;

		private ObscuredString m_returnUrl;

		private ObscuredString m_currency;

		private ObscuredString m_orderId;

		private ObscuredString m_serviceCode;

		private ObscuredString m_status;

		private ObscuredString m_transactionId;

		private ObscuredLong m_purchaseTime;

		private List<NXAItemData> m_items;

		public int amount
		{
			get
			{
				return 0;
			}
		}

		public string authorizeUrl
		{
			get
			{
				return null;
			}
		}

		public string returnUrl
		{
			get
			{
				return null;
			}
		}

		public string currency
		{
			get
			{
				return null;
			}
		}

		public string orderId
		{
			get
			{
				return null;
			}
		}

		public string transactionId
		{
			get
			{
				return null;
			}
		}

		public long purchaseTime
		{
			get
			{
				return 0L;
			}
		}

		public List<NXAItemData> items
		{
			get
			{
				return null;
			}
		}

		protected override void DeserializeImpl(JsonData jsonData)
		{
		}
	}
}
