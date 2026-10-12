using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public abstract class CurveFsmAction : FsmStateAction
	{
		public enum Calculation
		{
			None = 0,
			AddToValue = 1,
			SubtractFromValue = 2,
			SubtractValueFromCurve = 3,
			MultiplyValue = 4,
			DivideValue = 5,
			DivideCurveByValue = 6
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

		private float[] distances;

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
	}
}
