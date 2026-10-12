using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiNavigationExplicitGetProperties : ComponentAction<Selectable>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmGameObject selectOnDown;

		public FsmGameObject selectOnUp;

		public FsmGameObject selectOnLeft;

		public FsmGameObject selectOnRight;

		private Selectable _selectable;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoGetValue()
		{
		}
	}
}
