using System.Collections.Generic;
using LitJson;

namespace CaoCaoIAB
{
	public class Inventory : ITransmittable
	{
		private Dictionary<string, Purchase> purchaseMap;

		private Dictionary<string, SkuDetails> skuMap;

		public Inventory()
		{
		}

		public Inventory(string json)
		{
		}

		protected override void DeserializeImpl(JsonData jsonData)
		{
		}

		private void DeserializePurchaseMap(JsonData _purchaseMap)
		{
		}

		private void DeserializeSkuDetails(JsonData _skuMap)
		{
		}

		public override void Serialize(JsonWriter writer)
		{
		}

		public bool HasPurchase(string sku)
		{
			return false;
		}

		public Purchase GetPurchase(string sku)
		{
			return null;
		}

		public List<Purchase> GetAllPurchases()
		{
			return null;
		}

		public SkuDetails GetSkuDetails(string sku)
		{
			return null;
		}

		public List<SkuDetails> GetAllSkuDetails()
		{
			return null;
		}

		public void RemovePurchase(string sku)
		{
		}

		public void RemoveSkuDetail(string sku)
		{
		}

		public void AddPurchase(Purchase purchase)
		{
		}

		public void AddDetails(SkuDetails detail)
		{
		}
	}
}
