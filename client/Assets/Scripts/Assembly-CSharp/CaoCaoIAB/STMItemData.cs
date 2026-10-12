using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

namespace CaoCaoIAB
{
	public class STMItemData : ITransmittable
	{
		private ObscuredInt m_itemId;

		private ObscuredInt m_itemCount;

		private ObscuredString m_description;

		private ObscuredInt m_price;

		protected override void DeserializeImpl(JsonData jsonData)
		{
		}

		public override void Serialize(JsonWriter writer)
		{
		}
	}
}
