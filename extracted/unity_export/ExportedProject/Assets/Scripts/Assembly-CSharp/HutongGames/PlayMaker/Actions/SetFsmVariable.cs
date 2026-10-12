using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetFsmVariable : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString fsmName;

		public FsmString variableName;

		[RequiredField]
		public FsmVar setValue;

		public bool everyFrame;

		private PlayMakerFSM targetFsm;

		private NamedVariable targetVariable;

		private GameObject cachedGameObject;

		private string cachedFsmName;

		private string cachedVariableName;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoSetFsmVariable()
		{
		}
	}
}
