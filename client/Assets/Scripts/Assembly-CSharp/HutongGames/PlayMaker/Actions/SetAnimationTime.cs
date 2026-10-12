using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetAnimationTime : BaseAnimationAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString animName;

		public FsmFloat time;

		public bool normalized;

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

		private void DoSetAnimationTime(GameObject go)
		{
		}
	}
}
