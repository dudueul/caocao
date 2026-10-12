using System.Collections.Generic;
using LitJson;

namespace ExchangeStore
{
	public class ExchangeStoreHistorys
	{
		private Dictionary<ulong, ExchangeStoreHistory> m_srl2ExchangeStoreHistory;

		private Dictionary<KeyValuePair<int, int>, ExchangeStoreHistory> m_idNslot2ExchangeStoreHistory;

		private List<ulong> m_srls;

		// C# has no syntax for parameterized property 'Item'.
		public ExchangeStoreHistory get_Item(ulong srl)
		{
			return null;
		}

		public void Deserialize(JsonData jsonData)
		{
		}

		private void ApplyChanges(List<ExchangeStoreHistory> upsertList, List<ExchangeStoreHistory> removeList)
		{
		}

		public ExchangeStoreHistory GetShopSlotHistory(int shopId, int shopSlot)
		{
			return null;
		}
	}
}
