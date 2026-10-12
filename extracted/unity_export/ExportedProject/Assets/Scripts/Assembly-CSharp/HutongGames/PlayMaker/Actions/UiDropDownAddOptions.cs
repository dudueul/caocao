using System.Collections.Generic;
using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiDropDownAddOptions : ComponentAction<Dropdown>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString[] optionText;

		public FsmObject[] optionImage;

		private Dropdown dropDown;

		private List<Dropdown.OptionData> options;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoAddOptions()
		{
		}
	}
}
