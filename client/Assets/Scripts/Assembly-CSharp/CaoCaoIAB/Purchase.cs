using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

namespace CaoCaoIAB
{
	public class Purchase : ITransmittable
	{
		public ObscuredString itemType { get; private set; }

		public ObscuredString orderId { get; private set; }

		public ObscuredString packageName { get; private set; }

		public ObscuredString sku { get; private set; }

		public ObscuredLong purchaseTime { get; private set; }

		public ObscuredInt purchaseState { get; private set; }

		public ObscuredString developerPayload { get; private set; }

		public ObscuredString token { get; private set; }

		public ObscuredString signature { get; private set; }

		public ObscuredString originalJson { get; private set; }

		public ObscuredString receipt { get; private set; }

		public Purchase()
		{
		}

		public Purchase(string json)
		{
		}

		public Purchase(JsonData jsonData)
		{
		}

		public Purchase(NXAItemData item, string token)
		{
		}

		public Purchase(STMItemData item, string sku, string orderId)
		{
		}

		public override string ToString()
		{
			return null;
		}

		protected override void DeserializeImpl(JsonData jsonData)
		{
		}

		public override void Serialize(JsonWriter writer)
		{
		}

		public string GetSerializedString()
		{
			return null;
		}
	}
}
