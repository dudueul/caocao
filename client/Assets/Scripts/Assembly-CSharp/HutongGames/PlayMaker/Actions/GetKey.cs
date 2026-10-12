using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetKey : FsmStateAction
	{
		[RequiredField]
		public KeyCode key;

		[RequiredField]
		public FsmBool storeResult;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoGetKey()
		{
		}
	}
}
