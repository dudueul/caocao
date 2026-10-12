using UnityEngine;

namespace CaocaoLobby
{
	public class Unit : MonoBehaviour
	{
		[SerializeField]
		private UILabelLocalized unitName;

		[SerializeField]
		private UILabelLocalized unitLevel;

		[SerializeField]
		private UnitDataForRenderer unitRenderer;

		private const string levelFormat = "LV {0}";

		private UserUnit userUnit;

		public void UpdateUI(UserUnit userUnit)
		{
		}

		public void PlayAnimation()
		{
		}

		private void OnDeserializeUnit()
		{
		}

		public void UnregistUnitDeserializeCallback()
		{
		}

		private void OnDestroy()
		{
		}
	}
}
