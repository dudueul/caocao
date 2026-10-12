using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiDropDownClearOptions : ComponentAction<Dropdown>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		private Dropdown dropDown;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
