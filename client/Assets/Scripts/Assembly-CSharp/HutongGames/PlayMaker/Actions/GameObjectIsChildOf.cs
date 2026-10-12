using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GameObjectIsChildOf : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmGameObject isChildOf;

		public FsmEvent trueEvent;

		public FsmEvent falseEvent;

		[RequiredField]
		public FsmBool storeResult;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoIsChildOf(GameObject go)
		{
		}
	}
}
