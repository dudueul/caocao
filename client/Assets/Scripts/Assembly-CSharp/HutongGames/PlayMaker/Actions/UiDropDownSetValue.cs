using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiDropDownSetValue : ComponentAction<Dropdown>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmInt value;

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

		private void SetValue()
		{
		}
	}
}
