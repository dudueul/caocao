using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public abstract class AnimateFsmAction : FsmStateAction
	{
		public enum Calculation
		{
			None = 0,
			SetValue = 1,
			AddToValue = 2,
			SubtractFromValue = 3,
			SubtractValueFromCurve = 4,
			MultiplyValue = 5,
			DivideValue = 6,
			DivideCurveByValue = 7
		}

		public FsmFloat time;

		public FsmFloat speed;

		public FsmFloat delay;

		public FsmBool ignoreCurveOffset;

		public FsmEvent finishEvent;

		public bool realTime;

		private float startTime;

		private float currentTime;

		private float[] endTimes;

		private float lastTime;

		private float deltaTime;

		private float delayTime;

		private float[] keyOffsets;

		protected AnimationCurve[] curves;

		protected Calculation[] calculations;

		protected float[] resultFloats;

		protected float[] fromFloats;

		protected float[] toFloats;

		protected bool finishAction;

		protected bool isRunning;

		protected bool looping;

		private bool start;

		private float largestEndTime;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		protected void Init()
		{
		}

		public override void OnUpdate()
		{
		}

		private void CheckStart()
		{
		}

		private void UpdateTime()
		{
		}

		public void UpdateAnimation()
		{
		}

		private void CheckFinished()
		{
		}
	}
}
