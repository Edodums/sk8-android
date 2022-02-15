package unibo.it.settings_api.presentation

enum class EscCommand(
    val commandName: String,
    val label: String,
    val options: List<EscCommandOptions>
) {
    CUTOFF_VOLTAGE(
        "cutoff_voltage", "CutOff Voltage", listOf(
            CutOff.PER_CELL_2_6,
            CutOff.PER_CELL_2_8,
            CutOff.PER_CELL_3_0,
            CutOff.PER_CELL_3_2,
            CutOff.PER_CELL_3_4,
            CutOff.NO_CUT_OFf
        )
    ),

    THROTTLE_LIMIT(
        "throttle_limit", "Throttle Limit", listOf(
            Percentage.PERCENT_0, Percentage.PERCENT_20, Percentage.PERCENT_30,
            Percentage.PERCENT_40, Percentage.PERCENT_50, Percentage.PERCENT_60,
            Percentage.PERCENT_70, Percentage.PERCENT_80, Percentage.PERCENT_90
        )
    ),

    PERCENT_BRAKING(
        "percent_braking", "Percent Braking", listOf(
            Percentage.PERCENT_10, Percentage.PERCENT_20, Percentage.PERCENT_30,
            Percentage.PERCENT_40, Percentage.PERCENT_50, Percentage.PERCENT_60,
            Percentage.PERCENT_70, Percentage.PERCENT_80, Percentage.PERCENT_100
        )
    ),

    INITIAL_ACC(
        "initial_acc",
        "Initial Acc",
        listOf(InitialAcc.LOW, InitialAcc.MEDIUM, InitialAcc.HIGH, InitialAcc.VERY_HIGH)
    ),

    MOTOR_TIMING(
        "motor_timing", "Motor Timing", listOf(
            MotorTiming.VERY_LOW,
            MotorTiming.LOW,
            MotorTiming.NORMAL,
            MotorTiming.HIGH,
            MotorTiming.VERY_HIGH,
        )
    ),

    MOTOR_ROTATION(
        "motor_rotation",
        "Motor Rotation",
        listOf(MotorRotation.NORMAL, MotorRotation.REVERSE)
    ),

    PERCENT_DRAG_BRAKE(
        "percent_drag_brake", "Percent Drag Brake", listOf(
            Percentage.PERCENT_0, Percentage.PERCENT_4, Percentage.PERCENT_8,
            Percentage.PERCENT_12, Percentage.PERCENT_15, Percentage.PERCENT_20,
            Percentage.PERCENT_25, Percentage.PERCENT_30
        )
    ),

    NEUTRAL_RANGE(
        "neutral_range", "Neutral Range", listOf(
            Percentage.PERCENT_2, Percentage.PERCENT_3,
            Percentage.PERCENT_5, Percentage.PERCENT_6
        )
    ),

    RUNNING_MODE(
        "running_mode",
        "Running Mode",
        listOf(RunningMode.FORWARD_ONLY, RunningMode.FWD_PSE_REV, RunningMode.FWD_REV)
    ),

    PERCENT_THROTTLE_REVERSE(
        "percent_throttle_reverse", "Percent Throttle Reverse", listOf(
            Percentage.PERCENT_20, Percentage.PERCENT_30, Percentage.PERCENT_40,
            Percentage.PERCENT_50, Percentage.PERCENT_60, Percentage.PERCENT_70,
            Percentage.PERCENT_80, Percentage.PERCENT_90, Percentage.PERCENT_100
        )
    ),

    BEC_OUTPUT("bec_output", "Bec Output", listOf(BecOutput.V_6_0, BecOutput.V_8_4));
}

fun getByCommandName(commandName: String): EscCommand {
    return EscCommand.values().toList().first { it.commandName == commandName }
}


interface EscCommandOptions {
    val option: String
    val label: String
}

internal enum class Percentage(override val option: String, override val label: String) :
    EscCommandOptions {
    PERCENT_10("_PerCent_10", "10 %"),
    PERCENT_20("_PerCent_20", "20 %"),
    PERCENT_30("_PerCent_30", "30 %"),
    PERCENT_40("_PerCent_40", "40 %"),
    PERCENT_50("_PerCent_50", "50 %"),
    PERCENT_60("_PerCent_60", "60 %"),
    PERCENT_70("_PerCent_70", "70 %"),
    PERCENT_80("_PerCent_80", "80 %"),
    PERCENT_90("_PerCent_90", "90 %"),
    PERCENT_100("_PerCent_100", "100 %"),
    PERCENT_2("_PerCent_2", "2 %"),
    PERCENT_3("_PerCent_3", "3 %"),
    PERCENT_5("_PerCent_5", "5 %"),
    PERCENT_6("_PerCent_6", "6 %"),
    PERCENT_0("_PerCent_0", "0 %"),
    PERCENT_4("_PerCent_4", "4 %"),
    PERCENT_8("_PerCent_8", "8 %"),
    PERCENT_12("_PerCent_12", "12 %"),
    PERCENT_15("_PerCent_15", "15 %"),
    PERCENT_25("_PerCent_25", "25 %"),
}

internal enum class CutOff(override val option: String, override val label: String) :
    EscCommandOptions {
    PER_CELL_2_6("_2_6_per_cell", "2.6V/Cell"),
    PER_CELL_2_8("_2_8_per_cell", "2.8V/Cell"),
    PER_CELL_3_0("_3_0_per_cell", "3.0V/Cell"),
    PER_CELL_3_2("_3_2_per_cell", "3.2V/Cell"),
    PER_CELL_3_4("_3_4_per_cell", "3.4V/Cell"),
    NO_CUT_OFf("_no_cut_off", "No Cut-Off"),
}

internal enum class RunningMode(override val option: String, override val label: String) :
    EscCommandOptions {
    FORWARD_ONLY("_forward_only", "Forward Only"),
    FWD_PSE_REV("_fwd_pse_rev", "Forward/Pause/Reverse"),
    FWD_REV("_fwd_rev", "Forward/Reverse"),
}

internal enum class MotorRotation(override val option: String, override val label: String) :
    EscCommandOptions {
    NORMAL("_normal", "Normal"),
    REVERSE("_reverse", "Reverse"),
}


internal enum class InitialAcc(override val option: String, override val label: String) :
    EscCommandOptions {
    LOW("low", "Low"),
    MEDIUM("medium", "Medium"),
    HIGH("high", "High"),
    VERY_HIGH("very_high", "Very High"),
}

internal enum class MotorTiming(override val option: String, override val label: String) :
    EscCommandOptions {
    VERY_LOW("very_low", "Very Low"),
    LOW("low", "Low"),
    NORMAL("normal", "Normal"),
    HIGH("high", "High"),
    VERY_HIGH("very_high", "Very High")
}


internal enum class BecOutput(override val option: String, override val label: String) :
    EscCommandOptions {
    V_6_0("V_6_0", "6.0 V"),
    V_8_4("V_8_4", "8.4 V"),
}