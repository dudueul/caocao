using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

namespace CaoCaoIAB
{
	public class NXAItemData : ITransmittable
	{
		private ObscuredString m_itemId;

		private ObscuredString m_itemTransactionId;

		private ObscuredString m_transactionId;

		private ObscuredString m_name;

		private ObscuredString m_description;

		private ObscuredString m_status;

		private ObscuredInt m_price;

		private ObscuredBool m_prepayedOnly;

		public string transactionId
		{
			get
			{
				return null;
			}
		}

		public string itemTransactionId
		{
			get
			{
				return null;
			}
		}

		public string sku
		{
			get
			{
				return null;
			}
		}

		public int status { get; private set; }

		protected override void DeserializeImpl(JsonData jsonData)
		{
		}

		public override void Serialize(JsonWriter writer)
		{
		}

		private int GetPurchaseState(string itemState)
		{
			return 0;
		}
	}
}
