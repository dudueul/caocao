using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiDropDownGetSelectedData : ComponentAction<Dropdown>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmInt index;

		public FsmString getText;

		public FsmObject getImage;

		public bool everyFrame;

		private Dropdown dropDown;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void GetValue()
		{
		}
	}
}
