using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class FsmStateSwitch : FsmStateAction
	{
		[RequiredField]
		public FsmGameObject gameObject;

		public FsmString fsmName;

		public FsmString[] compareTo;

		public FsmEvent[] sendEvent;

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

		private void DoFsmStateSwitch()
		{
		}
	}
}
