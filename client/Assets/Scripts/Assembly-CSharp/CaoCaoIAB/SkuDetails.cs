using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

namespace CaoCaoIAB
{
	public class SkuDetails : ITransmittable
	{
		private JsonData jsonData;

		public ObscuredString itemType { get; private set; }

		public ObscuredString sku { get; private set; }

		public ObscuredString type { get; private set; }

		public ObscuredString price { get; private set; }

		public ObscuredString title { get; private set; }

		public ObscuredString description { get; private set; }

		public ObscuredString currencyCode { get; private set; }

		public ObscuredString priceMicros { get; private set; }

		public ObscuredString json { get; private set; }

		public ObscuredString priceValue { get; private set; }

		public SkuDetails()
		{
		}

		public SkuDetails(string json)
		{
		}

		public SkuDetails(JsonData jsonData)
		{
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

		public double GetPriceNumber()
		{
			return 0.0;
		}
	}
}
