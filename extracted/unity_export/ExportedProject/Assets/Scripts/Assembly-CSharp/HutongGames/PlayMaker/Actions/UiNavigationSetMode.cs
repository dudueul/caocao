using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiNavigationSetMode : ComponentAction<Selectable>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public Navigation.Mode navigationMode;

		public FsmBool resetOnExit;

		private Selectable selectable;

		private Navigation _navigation;

		private Navigation.Mode originalValue;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetValue()
		{
		}

		public override void OnExit()
		{
		}
	}
}
