namespace HutongGames.PlayMaker.Actions
{
	public class GetIPhoneSettings : FsmStateAction
	{
		public FsmBool getScreenCanDarken;

		public FsmString getUniqueIdentifier;

		public FsmString getName;

		public FsmString getModel;

		public FsmString getSystemName;

		public FsmString getGeneration;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
