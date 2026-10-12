using UnityEngine;
using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiNavigationExplicitSetProperties : ComponentAction<Selectable>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmGameObject selectOnDown;

		public FsmGameObject selectOnUp;

		public FsmGameObject selectOnLeft;

		public FsmGameObject selectOnRight;

		public FsmBool resetOnExit;

		private Selectable selectable;

		private Navigation navigation;

		private Navigation originalState;

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

		private static T GetComponentFromFsmGameObject<T>(FsmGameObject variable) where T : Component
		{
			return null;
		}
	}
}
