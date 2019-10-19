package com.epam.fitness.filter.helper;

import com.epam.fitness.entity.user.User;
import com.epam.fitness.entity.user.UserRole;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

@RunWith(DataProviderRunner.class)
public class CommandAccessControllerTest {

    private static final User ADMIN =
            new User(null, "", "", UserRole.ADMIN, "", "", 0);
    private static final User TRAINER =
            new User(null, "", "", UserRole.TRAINER, "", "", 0);
    private static final User CLIENT =
            new User(null, "", "", UserRole.CLIENT, "", "", 0);
    private static final String ADMIN_COMMAND = "setUserDiscount";

    private CommandAccessController controller = new CommandAccessController();

    @DataProvider
    public static Object[][] adminCommandsDataProvider(){
        return new Object[][]{
                { "showUsersPage" },
                { "setUserDiscount" }
        };
    }

    @DataProvider
    public static Object[][] trainerCommandsDataProvider(){
        return new Object[][]{
                { "assignNutritionType" },
                { "showTrainerClients" },
                { "showTrainerClientOrders" }
        };
    }

    @DataProvider
    public static Object[][] clientCommandsDataProvider(){
        return new Object[][]{
                { "getMembership" },
                { "showOrderPage" },
                { "showOrders" },
                { "sendFeedback "},
                { "showAssignments" },
                { "changeAssignment" },
                { "changeAssignmentStatus" }
        };
    }

    @Test
    @UseDataProvider("adminCommandsDataProvider")
    public void testHasAccessShouldReturnTrueWhenAdminAndAdminCommandSupplied(String command){

        //given

        //when
        boolean actual = controller.hasAccess(command, ADMIN);

        //then
        Assert.assertTrue(actual);
    }

    @Test
    @UseDataProvider("trainerCommandsDataProvider")
    public void testHasAccessShouldReturnTrueWhenTrainerAndTrainerCommandSupplied(String command){

        //given

        //when
        boolean actual = controller.hasAccess(command, TRAINER);

        //then
        Assert.assertTrue(actual);
    }

    @Test
    @UseDataProvider("clientCommandsDataProvider")
    public void testHasAccessShouldReturnTrueWhenClientAndClientCommandSupplied(String command){

        //given

        //when
        boolean actual = controller.hasAccess(command, CLIENT);

        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void testHasAccessShouldReturnFalseWhenNullUserSupplied(){
        //given

        //when
        boolean actual = controller.hasAccess(anyString(), null);

        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testHasAccessShouldReturnFalseWhenNullCommandSupplied(){
        //given

        //when
        boolean actual = controller.hasAccess(null, CLIENT);

        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void testHasAccessShouldReturnFalseWhenHasNotAccess(){
        //given

        //when
        boolean actual = controller.hasAccess(ADMIN_COMMAND, CLIENT);

        //then
        Assert.assertFalse(actual);
    }

}