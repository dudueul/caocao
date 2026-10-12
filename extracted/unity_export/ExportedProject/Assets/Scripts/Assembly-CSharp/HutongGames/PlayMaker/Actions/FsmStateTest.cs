using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class FsmStateTest : FsmStateAction
	{
		[RequiredField]
		public FsmGameObject gameObject;

		public FsmString fsmName;

		[RequiredField]
		public FsmString stateName;

		public FsmEvent trueEvent;

		public FsmEvent falseEvent;

		public FsmBool storeResult;

		public bool everyFrame;

		private GameObject previousGo;

		private PlayMakerFSM fsm;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoFsmStateTest()
		{
		}
	}
}
