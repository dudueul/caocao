using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class ActivateGameObject : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmBool activate;

		public FsmBool recursive;

		public bool resetOnExit;

		public bool everyFrame;

		private GameObject activatedGameObject;

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

		private void DoActivateGameObject()
		{
		}

		public void SetActiveRecursively(GameObject go, bool state)
		{
		}
	}
}
