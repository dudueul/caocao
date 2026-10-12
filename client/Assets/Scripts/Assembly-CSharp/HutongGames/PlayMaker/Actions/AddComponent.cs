using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class AddComponent : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString component;

		public FsmObject storeComponent;

		public FsmBool removeOnExit;

		private Component addedComponent;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnExit()
		{
		}

		private void DoAddComponent()
		{
		}
	}
}
