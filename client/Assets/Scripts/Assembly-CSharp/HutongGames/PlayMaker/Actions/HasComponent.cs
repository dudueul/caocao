using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class HasComponent : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString component;

		public FsmBool removeOnExit;

		public FsmEvent trueEvent;

		public FsmEvent falseEvent;

		public FsmBool store;

		public bool everyFrame;

		private Component aComponent;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		public override void OnExit()
		{
		}

		private void DoHasComponent(GameObject go)
		{
		}
	}
}
