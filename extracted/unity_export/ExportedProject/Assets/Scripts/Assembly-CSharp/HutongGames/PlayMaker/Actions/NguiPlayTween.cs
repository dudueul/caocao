using AnimationOrTween;
using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class NguiPlayTween : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault tweenTarget;

		public FsmInt tweenGroup;

		public Direction playDirection;

		public FsmBool resetOnPlay;

		public FsmBool resetIfDisabled;

		public EnableCondition ifDisabledOnPlay;

		public DisableCondition disableWhenFinished;

		public FsmBool includeChildren;

		public FsmEvent tweeningFinishedEvent;

		private UITweener[] mTweens;

		private GameObject _tweenTarget;

		private int mActive;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		public void PlayTweeners()
		{
		}

		private void OnFinished()
		{
		}
	}
}
