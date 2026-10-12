using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetAnimationWeight : BaseAnimationAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString animName;

		public FsmFloat weight;

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

		private void DoSetAnimationWeight(GameObject go)
		{
		}
	}
}
