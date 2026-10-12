namespace HutongGames.PlayMaker.Actions
{
	public abstract class EaseFsmAction : FsmStateAction
	{
		protected delegate float EasingFunction(float start, float end, float value);

		public enum EaseType
		{
			easeInQuad = 0,
			easeOutQuad = 1,
			easeInOutQuad = 2,
			easeInCubic = 3,
			easeOutCubic = 4,
			easeInOutCubic = 5,
			easeInQuart = 6,
			easeOutQuart = 7,
			easeInOutQuart = 8,
			easeInQuint = 9,
			easeOutQuint = 10,
			easeInOutQuint = 11,
			easeInSine = 12,
			easeOutSine = 13,
			easeInOutSine = 14,
			easeInExpo = 15,
			easeOutExpo = 16,
			easeInOutExpo = 17,
			easeInCirc = 18,
			easeOutCirc = 19,
			easeInOutCirc = 20,
			linear = 21,
			spring = 22,
			bounce = 23,
			easeInBack = 24,
			easeOutBack = 25,
			easeInOutBack = 26,
			elastic = 27,
			punch = 28
		}

		[RequiredField]
		public FsmFloat time;

		public FsmFloat speed;

		public FsmFloat delay;

		public EaseType easeType;

		public FsmBool reverse;

		public FsmEvent finishEvent;

		public bool realTime;

		protected EasingFunction ease;

		protected float runningTime;

		protected float lastTime;

		protected float startTime;

		protected float deltaTime;

		protected float delayTime;

		protected float percentage;

		protected float[] fromFloats;

		protected float[] toFloats;

		protected float[] resultFloats;

		protected bool finishAction;

		protected bool start;

		protected bool finished;

		protected bool isRunning;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnExit()
		{
		}

		public override void OnUpdate()
		{
		}

		protected void UpdatePercentage()
		{
		}

		protected void SetEasingFunction()
		{
		}

		protected float linear(float start, float end, float value)
		{
			return 0f;
		}

		protected float clerp(float start, float end, float value)
		{
			return 0f;
		}

		protected float spring(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeInQuad(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeOutQuad(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeInOutQuad(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeInCubic(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeOutCubic(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeInOutCubic(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeInQuart(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeOutQuart(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeInOutQuart(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeInQuint(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeOutQuint(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeInOutQuint(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeInSine(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeOutSine(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeInOutSine(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeInExpo(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeOutExpo(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeInOutExpo(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeInCirc(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeOutCirc(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeInOutCirc(float start, float end, float value)
		{
			return 0f;
		}

		protected float bounce(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeInBack(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeOutBack(float start, float end, float value)
		{
			return 0f;
		}

		protected float easeInOutBack(float start, float end, float value)
		{
			return 0f;
		}

		protected float punch(float amplitude, float value)
		{
			return 0f;
		}

		protected float elastic(float start, float end, float value)
		{
			return 0f;
		}
	}
}
