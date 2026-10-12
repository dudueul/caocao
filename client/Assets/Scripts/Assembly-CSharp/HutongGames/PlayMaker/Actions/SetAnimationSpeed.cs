using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetAnimationSpeed : BaseAnimationAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString animName;

		public FsmFloat speed;

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

		private void DoSetAnimationSpeed(GameObject go)
		{
		}
	}
}
