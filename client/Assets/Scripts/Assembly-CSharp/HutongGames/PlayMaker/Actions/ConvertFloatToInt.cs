namespace HutongGames.PlayMaker.Actions
{
	public class ConvertFloatToInt : FsmStateAction
	{
		public enum FloatRounding
		{
			RoundDown = 0,
			RoundUp = 1,
			Nearest = 2
		}

		[RequiredField]
		public FsmFloat floatVariable;

		[RequiredField]
		public FsmInt intVariable;

		public FloatRounding rounding;

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

		private void DoConvertFloatToInt()
		{
		}
	}
}
